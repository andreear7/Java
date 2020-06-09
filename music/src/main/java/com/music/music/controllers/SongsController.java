package com.music.music.controllers;

import com.github.javafaker.Faker;
import com.lowagie.text.DocumentException;
import com.music.music.models.Song;
import com.music.music.models.Vote;
import com.music.music.repository.SongRepository;
import com.music.music.repository.UserRepository;
import com.music.music.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.validation.Valid;
import java.io.*;

@Controller
@RequestMapping("/songs")
public class SongsController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private VoteRepository voteRepository;

    private String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates\\");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("songs", songRepository.findByOrderByVoturiDesc());
        return templateEngine.process("top-pdf", context);
    }

    public void generatePdfFromHtml(String html) throws DocumentException, IOException {
        String outputFolder = "C:\\Users\\Dumitrascu\\Desktop\\" + "top.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
    }


    @GetMapping("/top/{id}")
    public String showTop(@PathVariable int id, Model model, Model userModel) {
        model.addAttribute("songs", songRepository.findByOrderByVoturiDesc());
        userModel.addAttribute("user", userRepository.findById(id));
        return "top";
    }

    @GetMapping("/addsong/{id}")
    public String showAddSongForm(Song song, Model model, @PathVariable int id) {
        model.addAttribute("user", userRepository.findById(id));
        return "add-song";
    }

    @GetMapping("/generatepdf/{id}")
    public String generatePDF(Model model, Model userModel, @PathVariable int id) {
        try {
            generatePdfFromHtml(parseThymeleafTemplate());
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("songs", songRepository.findByOrderByVoturiDesc());
        userModel.addAttribute("user", userRepository.findById(id));
        return "top";
    }

    @PostMapping("/vote/{id}/{idSong}")
    public String voteSong(@PathVariable int id, @PathVariable int idSong, Model model, Model userModel) {
        Song song = songRepository.findById(idSong);
        if (voteRepository.findByIdUtilizatorAndIdMelodie(id, idSong) == null) {
            Vote vote = new Vote(idSong, id);
            voteRepository.save(vote);
            song.setVoturi(song.getVoturi() + 1);
            songRepository.save(song);
            model.addAttribute("songs", songRepository.findByOrderByVoturiDesc());
            userModel.addAttribute("user", userRepository.findById(id));
            return "index";
        }
        model.addAttribute("songs", songRepository.findByOrderByVoturiDesc());
        userModel.addAttribute("user", userRepository.findById(id));
        return "index-votat";
    }

    @PostMapping("/delete/{idUser}/{id}")
    public String deleteSong(@PathVariable int id, @PathVariable int idUser, Model model, Model userModel) {
        Song song = songRepository.findById(id);
        songRepository.delete(song);
        model.addAttribute("songs", songRepository.findAll());
        userModel.addAttribute("user", userRepository.findById(idUser));
        return "index";
    }

    @PostMapping(path = "/songadded/{idUser}")
    public String addSong(@Valid Song song, Model model, Model userModel, @PathVariable int idUser) {
        song.setIdUtilizator(idUser);
        song.setVoturi(0);
        songRepository.save(song);
        model.addAttribute("songs", songRepository.findAll());
        userModel.addAttribute("user", userRepository.findById(idUser));
        return "index";

    }

    @PostMapping(path = "/songaddedrandomly/{idUser}")
    public String addSong(Model model, Model userModel, @PathVariable int idUser) {
        Faker faker = new Faker();
        String nume = faker.cat().name();
        String autor = faker.artist().name();
        String descriere = faker.lorem().paragraph();
        String link = "www.youtube.com/" + faker.code().asin();
        String gen = faker.music().genre();
        Song song = new Song();
        song.setNumeMelodie(nume);
        song.setAutor(autor);
        song.setDescriere(descriere);
        song.setLinkMelodie(link);
        song.setGen(gen);
        song.setVoturi(0);
        song.setIdUtilizator(idUser);
        songRepository.save(song);
        model.addAttribute("songs", songRepository.findAll());
        userModel.addAttribute("user", userRepository.findById(idUser));
        return "index";

    }
}

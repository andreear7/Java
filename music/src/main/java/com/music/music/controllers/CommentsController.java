package com.music.music.controllers;

import com.music.music.models.Comment;
import com.music.music.repository.CommentRepository;
import com.music.music.repository.SongRepository;
import com.music.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


@Controller
@RequestMapping("/comments")
public class CommentsController {
    private void sendmail(String email) throws MessagingException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("music.app.adm123@gmail.com", "music.app.ADM123");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("music.app.adm123@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Comentariu music app");
        msg.setContent("Comentariul tau a fost inregistrat, multumim pentru timpul acordat!", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Comentariul tau a fost inregistrat, multumim pentru timpul acordat!", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;

    @GetMapping("/addcomment/{id}")
    public String showcommentForm(Comment comment, Model model, @PathVariable int id) {
        model.addAttribute("user", userRepository.findById(id));
        return "add-comment";
    }

    @PostMapping(path = "/commentadded/{idUser}")
    public String addSong(Comment comment, Model model, Model userModel, @PathVariable int idUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-comment";
        }

        comment.setIdUtilizator(idUser);
        commentRepository.save(comment);
        try {
            sendmail(comment.getEmail());
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("songs", songRepository.findAll());
        userModel.addAttribute("user", userRepository.findById(idUser));
        return "index";
    }

}

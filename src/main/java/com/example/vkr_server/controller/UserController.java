package com.example.vkr_server.controller;

import com.example.vkr_server.dto.FilterMarkerDto;
import com.example.vkr_server.dto.UserDto;
import com.example.vkr_server.mapper.UserMapper;
import com.example.vkr_server.model.*;
import com.example.vkr_server.repository.DislikeRepository;
import com.example.vkr_server.repository.GroupChatRepository;
import com.example.vkr_server.repository.LikeRepository;
import com.example.vkr_server.repository.MarkerRepository;
import com.example.vkr_server.service.MarkerService;
import com.example.vkr_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MarkerService markerService;

    @Autowired
    GroupChatRepository groupChatRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    DislikeRepository dislikeRepository;

    @Autowired
    MarkerRepository markerRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<UserDto> allUsers() {
        List<User> users = this.userService.getAll();
        return new ResponseEntity(users.stream().map(u -> UserMapper.INSTANCE.toDto(u)), HttpStatus.OK);
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getAllFriends(Principal principal) {
        Optional<User> user = this.userService.findByEmail(principal.getName());
        List<User> users = this.userService.getFriends(user.get());
        return new ResponseEntity(users.stream().map(u -> UserMapper.INSTANCE.toDto(u)), HttpStatus.OK);
    }

    @RequestMapping(value = "/not_friends", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getAllNotFriends(Principal principal) {
        Optional<User> user = this.userService.findByEmail(principal.getName());
        List<User> users = this.userService.getNotFriends(user.get());
        return new ResponseEntity(users.stream().map(u -> UserMapper.INSTANCE.toDto(u)), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        List<GroupChat> groupChat = this.groupChatRepository.findAll();
        System.out.println("List<GroupChat> groupChat"+this.groupChatRepository.findAll());
        for (GroupChat chat : groupChat) {
            Optional<User> currentUser = userService.findByEmail(user.getUsername());
            User u = this.userService.getById(chat.getId());
            u.addFriend(currentUser.get());
            userService.save(u);

            currentUser = userService.findById(chat.getId());
            u = this.userService.getById(user.getId());
            u.addFriend(currentUser.get());
            userService.save(u);
            System.out.println("u.getUsername()"+u.getUsername());
            System.out.println(currentUser.get().getUsername());
        }
        this.userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        User u = this.userService.getById(id);
        return new ResponseEntity<>(UserMapper.INSTANCE.toDto(u), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/add_friend", method = RequestMethod.GET)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, Principal principal) {
        Optional<User> currentUser = userService.findByEmail(principal.getName());
        User u = this.userService.getById(id);
        u.addFriend(currentUser.get());
        userService.save(u);
        return new ResponseEntity<>(UserMapper.INSTANCE.toDto(u), HttpStatus.OK);
    }

    @RequestMapping(value ="/save_marker", method = RequestMethod.POST)
    public void saveMarker(@RequestBody Marker marker) {
        Marker newMarker = new Marker();
        newMarker.setAuthor(marker.getAuthor());
        newMarker.setType(marker.getType());
        newMarker.setRating(0L);
        newMarker.setCoord(marker.getCoord());
        newMarker.setDate(String.valueOf(LocalDate.now()));
        newMarker.setDescription(marker.getDescription());
        newMarker.setRegion(marker.getRegion());
        markerService.addMarker(newMarker);
    }

    @RequestMapping(value = "/get_user_marker/{author}", method = RequestMethod.GET)
    public List<Marker> getMarkerUserMarker(@PathVariable("author") String author) {
        System.out.println("markerService.getAllMarker()"+markerService.getAllMarker());
        return markerService.getMarkerByUser(author);
    }

    @RequestMapping(value = "/get_region_marker/{region}", method = RequestMethod.GET)
    public List<Marker> getMarkerRegionMarker(@PathVariable("region") String region) {
        return markerService.getMarkerByRegion(region);
    }

    @RequestMapping(value = "/marker", method = RequestMethod.GET)
    public List<Marker> marker() {
        System.out.println("markerService.getAllMarker()"+markerService.getAllMarker());
        return markerService.getAllMarker();
    }

    @RequestMapping(value ="/edit_marker",method = RequestMethod.PUT)
    public void editMarker(@RequestBody Marker marker) {
        System.out.println(marker.getDescription());
        Marker newMarker = new Marker();
        newMarker.setId(marker.getId());
        newMarker.setType(marker.getType());
        newMarker.setCoord(marker.getCoord());
        newMarker.setDescription(marker.getDescription());
        newMarker.setAuthor(marker.getAuthor());
        newMarker.setDate(marker.getDate());
        newMarker.setRating(marker.getRating());
        newMarker.setCoord(marker.getCoord());
        newMarker.setRegion(marker.getRegion());
        markerService.addMarker(newMarker);
    }

    @RequestMapping(value ="/delete_marker/{id}",method = RequestMethod.POST)
    public void deleteMarker(@PathVariable("id") Long id) {
        markerService.deleteMarkerById(id);
    }

    @RequestMapping(value ="/delete_like",method = RequestMethod.POST)
    public void deleteLike(@RequestBody Like retingDTO) {
        likeRepository.deleteByUsernameAndIdmarker(retingDTO.getUsername(),retingDTO.getIdmarker());
    }

    @RequestMapping(value ="/delete_dislike",method = RequestMethod.POST)
    public void deleteDisDislike(@RequestBody Dislike retingDTO) {
        dislikeRepository.deleteByUsernameAndIdmarker(retingDTO.getUsername(),retingDTO.getIdmarker());
    }

    @RequestMapping(value ="/add_like",method = RequestMethod.POST)
    public void putLike(@RequestBody Like like) {
        likeRepository.save(like);
    }

    @RequestMapping(value = "/add_dislike", method = RequestMethod.POST)
    public void putDislike(@RequestBody Dislike dislike) {
        dislikeRepository.save(dislike);
    }

    @RequestMapping(value ="/check_add_like",method = RequestMethod.POST)
    public boolean checkPutLike(@RequestBody Like like) {
        System.out.println(like.getUsername());
        return likeRepository.existsByUsernameAndIdmarker(like.getUsername(),like.getIdmarker());
    }

    @RequestMapping(value = "/check_add_dislike", method = RequestMethod.POST)
    public boolean checkPutDislike(@RequestBody Dislike dislike) {
        return dislikeRepository.existsByUsernameAndIdmarker(dislike.getUsername(),dislike.getIdmarker());
    }

    @RequestMapping(value = "/filter_dislike", method = RequestMethod.POST)
    public List<Marker> checkPutDislike(@RequestBody FilterMarkerDto filterMarkerDto) {
        System.out.println(filterMarkerDto.getAuthor());
        System.out.println(filterMarkerDto.getType());
//        return markerRepository.findByAuthorAndType(filterMarkerDto.getAuthor(),filterMarkerDto.getType());
        String author = filterMarkerDto.getAuthor();
        String type = filterMarkerDto.getType();
        // Создание списка для хранения результатов
        List<Marker> resultList;

        // Проверка наличия обоих параметров
        if (author != null && type != null) {
            // Выполнение запроса с обоими параметрами
            // Добавление результатов в список resultList
            return resultList = markerRepository.findByAuthorAndType(author, type);
        } else if (author != null) {
            // Выполнение запроса только с параметром author
            // Добавление результатов в список resultList
            return resultList = markerRepository.findByAuthor(author);
        } else if (type != null) {
            // Выполнение запроса только с параметром type
            // Добавление результатов в список resultList
            return resultList = markerRepository.findByType(type);
        }
        return null;
    }

}

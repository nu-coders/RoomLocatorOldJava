package com.nucoders.roomLocator.controller;

import com.nucoders.roomLocator.service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/v1/roomlocator"})
public class controller {
    private String projectUrl = "https://www.youtube.com/watch?v=ZzRqtW1C0Zo";
    @Autowired
    private service _service;

    @GetMapping("/")
    public ModelAndView welcome(){
        return new ModelAndView("redirect:" + projectUrl);
    }
    @GetMapping("/2")
    public String welcome2(){
        return "ret7:" + projectUrl;
    }

    @GetMapping("/b1")
    public List<String> getRoomsB1(){
        return _service.b1Rooms();
    }

    @GetMapping("/b2")
    public List<String> getRoomsB2(){
        return   _service.b2Rooms();
    }

    @GetMapping("/rooms")
    public List<String> getRooms(){
        return  _service.rooms();
    }


    @GetMapping("/room")
    @ResponseBody
    public List<String> getRoom(@RequestParam String id){
        return _service.room(id);
    }

    @GetMapping("/table")
    @ResponseBody
    public List<String> getRoomTable(@RequestParam String id){
        return _service.roomTable(id);
    }
}

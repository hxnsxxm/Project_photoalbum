package com.squarecross.photoalbum.controller;

import com.squarecross.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albums/{albumId}/photos")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

/*
    @RequestMapping(value="/{photoId}", method= RequestMethod.GET)
    public ResponseEntity<PhotoDto> getPhoto(@PathVariable("photoId") final long photoId) {

    }

*/
}

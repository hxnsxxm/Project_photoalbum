package com.squarecross.photoalbum.controller;

import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @RequestMapping(value="/{albumId}", method= RequestMethod.GET)
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable("albumId") final long albumId) {
        AlbumDto album = albumService.getAlbum(albumId);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @RequestMapping(value="/query", method=RequestMethod.GET)
    public ResponseEntity<AlbumDto> getAlbum(@RequestParam(value="albumId") final Long albumId) {
        AlbumDto album = albumService.getAlbum(albumId);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }
}

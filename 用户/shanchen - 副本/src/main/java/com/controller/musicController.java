package com.controller;

import com.domain.like;
import com.domain.music;
import com.untils.PageResult;
import com.untils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/music")
public class musicController {

    @Autowired
    public com.service.musicService musicService;

    @GetMapping("/SelectPage/{size}/{current}")
    public PageResult selectPage(@PathVariable Integer size, @PathVariable Integer current, music music) {
        if (music.getStatus() == null || music.getStatus().isEmpty()) {
            music.setStatus("approved"); // 只查询状态为approved的APP
        }
        return musicService.SelectPage(music, size, current);
    }

    @GetMapping("/SelectPageRe/{size}/{current}")
    public PageResult selectPageRe(@PathVariable Integer size, @PathVariable Integer current, music music) {
        return musicService.SelectPageRe(music, size, current);
    }

    @GetMapping("/SelectPageLike/{size}/{current}/{userId}")
    public PageResult selectPageLike(@PathVariable Integer size, @PathVariable Integer current, @PathVariable Integer userId, music music) {
        return musicService.SelectPageLike(music, size, current, userId);
    }

    @GetMapping("/pan/{userId}/{musicId}")
    public Boolean pan(@PathVariable Integer userId, @PathVariable Integer musicId) {
        return musicService.pan(userId, musicId);
    }

    @PostMapping("/add")
    public void add(@RequestBody like like) {
        musicService.add(like);
    }

    @GetMapping("/deleteById/{userId}/{musicId}")
    public void deleteById(@PathVariable Integer userId, @PathVariable Integer musicId) {
        musicService.deleteById(userId, musicId);
    }

    @PostMapping("/Save")
    public int Save(@RequestBody music music) {
        if (music.getStatus() == null || music.getStatus().isEmpty()) {
            music.setStatus("pending"); // 设置默认状态为pending
        }
        return musicService.insert(music);
    }

    @DeleteMapping("/Delete/{id}")
    public int Delete(@PathVariable Integer id) {
        return musicService.delete(id);
    }

    @PutMapping("/Update")
    public int Update(@RequestBody music music) {
        return musicService.edit(music);
    }

    @GetMapping("/findById/{id}")
    public music findById(@PathVariable Integer id) {
        return musicService.findById(id);
    }

    @PostMapping("/load")
    public String load(@RequestPart("file") MultipartFile file) {
        try {
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            byte[] bytes = file.getBytes();

            QiniuUtils qiniuUtils = new QiniuUtils();
            qiniuUtils.upload2Qiniu(bytes, fileName);

            return "http://rjdqzyqgi.hd-bkt.clouddn.com/" + fileName;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/Approve/{id}")
    public int Approve(@PathVariable Integer id) {
        music music = musicService.findById(id);
        if (music != null) {
            music.setStatus("approved");
            return musicService.edit(music);
        }
        return 0; // 如果找不到该APP，则返回0表示失败
    }
}

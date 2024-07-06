package com.controller;

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
    public PageResult selectPage(@PathVariable Integer size, @PathVariable Integer current, music music)
    {
        return  musicService.SelectPage(music,size,current);
    }

    @PostMapping("/Save")
    public  int Save(@RequestBody music music)
    {
        return musicService.insert(music);

    }
    @DeleteMapping("/Delete/{id}")
    public int Delete(@PathVariable Integer id)
    {

        return musicService.delete(id);
    }
    @PutMapping("/Update")
    public int Update(@RequestBody music music)
    {
        return  musicService.edit(music);
    }

    @GetMapping("/findById/{id}")
    public music findById(@PathVariable Integer id)
    {

        return musicService.findById(id);
    }
    @PostMapping("/load")

    // 查询所有音乐信息
    @GetMapping("/SelectPage/{size}/{current}")
    public PageResult adminSelectPage(@PathVariable Integer size, @PathVariable Integer current, music music) {
        return musicService.adminSelectPage(music, size, current);
    }

    // 更新音乐状态
    @PutMapping("/UpdateStatus/{id}")
    public int updateStatus(@PathVariable Integer id, @RequestParam String status) {
        return musicService.updateStatus(id, status);
    }

    public String load(@RequestPart("file") MultipartFile file)
    {
        try {
            UUID uuid = UUID.randomUUID();
            String fileName=uuid+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            byte[] bytes= file.getBytes();

            QiniuUtils qiniuUtils=new QiniuUtils();
            qiniuUtils.upload2Qiniu(bytes,fileName);

            return "http://rjdqzyqgi.hd-bkt.clouddn.com/"+fileName;
        }
        catch (Exception e)
        {
            return  null;
        }






    }

}

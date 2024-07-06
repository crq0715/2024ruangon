package com.service.Imple;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.likeDao;
import com.dao.musicDao;
import com.domain.like;
import com.domain.music;
import com.service.musicService;
import com.untils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class musicServiceImple implements musicService {
    @Autowired
    private musicDao musicDao;

    @Autowired
    private likeDao likeDao;

    @Override
    public PageResult SelectPage(music music, int size, int current) {
        try {
            IPage<music> page = new Page<>(current, size);
            QueryWrapper<music> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("status", "approved"); // 只查询审核通过的音乐信息
            if (music.getName() != null && !music.getName().isEmpty()) {
                queryWrapper.like("name", music.getName());
            }

            page = musicDao.selectPage(page, queryWrapper);
            while (page.getRecords().isEmpty() && current > 1) {
                page = new Page<>(--current, size);
                page = musicDao.selectPage(page, queryWrapper);
            }

            return new PageResult(page.getTotal(), page.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageResult SelectPageRe(music music, int size, int current) {
        try {
            IPage<music> page = new Page<>(current, size);
            QueryWrapper<music> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("status", "approved"); // 只查询审核通过的音乐信息
            queryWrapper.eq("re", "是");
            if (music.getName() != null && !music.getName().isEmpty()) {
                queryWrapper.like("name", music.getName());
            }

            page = musicDao.selectPage(page, queryWrapper);
            while (page.getRecords().isEmpty() && current > 1) {
                page = new Page<>(--current, size);
                page = musicDao.selectPage(page, queryWrapper);
            }

            return new PageResult(page.getTotal(), page.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageResult SelectPageLike(music music, int size, int current, int userId) {
        try {
            QueryWrapper<like> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("userId", userId);
            List<like> likes = likeDao.selectList(queryWrapper1);

            List<Integer> musicIds = new ArrayList<>();
            for (like l : likes) {
                musicIds.add(l.getMusicId());
            }

            IPage<music> page = new Page<>(current, size);
            QueryWrapper<music> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("status", "approved"); // 只查询审核通过的音乐信息
            if (music.getName() != null && !music.getName().isEmpty()) {
                queryWrapper.like("name", music.getName());
            }
            queryWrapper.in("id", musicIds);

            page = musicDao.selectPage(page, queryWrapper);
            while (page.getRecords().isEmpty() && current > 1) {
                page = new Page<>(--current, size);
                page = musicDao.selectPage(page, queryWrapper);
            }

            return new PageResult(page.getTotal(), page.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(music music) {
        return musicDao.insert(music);
    }

    @Override
    public int delete(int id) {
        return musicDao.deleteById(id);
    }

    @Override
    public int edit(music music) {
        return musicDao.updateById(music);
    }

    @Override
    public music findById(int id) {
        return musicDao.selectById(id);
    }

    @Override
    public Boolean pan(int userId, int musicId) {
        QueryWrapper<like> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("userId", userId);
        queryWrapper1.eq("musicId", musicId);
        List<like> list = likeDao.selectList(queryWrapper1);
        return !list.isEmpty();
    }

    @Override
    public void add(like like) {
        likeDao.insert(like);
    }

    @Override
    public void deleteById(int userId, int musicId) {
        QueryWrapper<like> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("userId", userId);
        queryWrapper1.eq("musicId", musicId);
        likeDao.delete(queryWrapper1);
    }
}

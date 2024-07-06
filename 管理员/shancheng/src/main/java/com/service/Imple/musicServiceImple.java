package com.service.Imple;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.musicDao;
import com.domain.music;
import com.service.musicService;
import com.untils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class musicServiceImple implements musicService {
    @Autowired
    private musicDao musicDao;

    @Override
    public PageResult SelectPage(music music, int size, int current) {
        try {
            IPage<music> page = new Page<>(current, size);
            QueryWrapper<music> queryWrapper = new QueryWrapper<>();
            if (music.getName() != null && !music.getName().isEmpty()) {
                queryWrapper.like("name", music.getName());
            }

            page = musicDao.selectPage(page, queryWrapper);
            while (page.getRecords().isEmpty() && current > 1) {
                page = new Page<>(current - 1, size);
                page = musicDao.selectPage(page, queryWrapper);
            }

            return new PageResult(page.getTotal(), page.getRecords());
        } catch (Exception e) {
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

    // 新增的方法，用于管理员端
    @Override
    public PageResult adminSelectPage(music music, int size, int current) {
        try {
            IPage<music> page = new Page<>(current, size);
            QueryWrapper<music> queryWrapper = new QueryWrapper<>();
            if (music.getName() != null && !music.getName().isEmpty()) {
                queryWrapper.like("name", music.getName());
            }

            page = musicDao.selectPage(page, queryWrapper);
            while (page.getRecords().isEmpty() && current > 1) {
                page = new Page<>(current - 1, size);
                page = musicDao.selectPage(page, queryWrapper);
            }

            return new PageResult(page.getTotal(), page.getRecords());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int updateStatus(int id, String status) {
        music music = musicDao.selectById(id);
        if (music != null) {
            music.setStatus(status);
            return musicDao.updateById(music);
        }
        return 0;
    }
}

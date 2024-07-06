package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.domain.music;
import com.domain.user;
import org.springframework.stereotype.Repository;

@Repository
public interface musicDao extends BaseMapper<music> {
}

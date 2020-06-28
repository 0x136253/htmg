package com.zstu.htmg.service.Impl;

import com.zstu.htmg.dto.GuestDetailDTO;
import com.zstu.htmg.mapper.GuestMapper;
import com.zstu.htmg.pojo.Guest;
import com.zstu.htmg.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:22
 */
@Service
public class GuestServeiceImpl implements GuestService {
    @Autowired
    private GuestMapper guestMapper;

    @Override
    public Guest getGuestDetail(int id) throws Exception {
        return guestMapper.selectGuestById(id);
    }

    @Override
    public GuestDetailDTO getGuestDetailAndTimeInfo(int id) throws Exception {
        return guestMapper.selectGuestDetailById(id);
    }
}

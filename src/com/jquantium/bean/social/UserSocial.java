package com.jquantium.bean.social;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
@Table(name = "social_users")
public class UserSocial {

    @Column(primary = true, length = 10, autoIncrement = false, unsigned = true)
    private int userId;


}

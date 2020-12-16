package com.example.vich_syst_rgr_back.modules.user.domain;

import com.example.vich_syst_rgr_back.core.domain.User;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * класс-представление инкассатора
 */

@Entity
@Table(name = "collector_users")
public class CollectorUser extends User {
}

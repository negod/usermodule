/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget.user;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import se.backede.generics.persistence.entity.GenericEntity;

/**
 *
 * @author joaki
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserEntity extends GenericEntity {

    private String name;
    private String password;

}

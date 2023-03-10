package com.capgemini.academia.dto;

import java.io.Serializable;

import lombok.*;

/**
 * Created by
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDTO implements Serializable {
 
	private String code;

    private String description;

    private Long idModule;
    
    private ApplicationEntry application;

}

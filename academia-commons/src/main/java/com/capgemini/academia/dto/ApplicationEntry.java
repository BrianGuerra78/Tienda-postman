package com.capgemini.academia.dto;

import java.util.List;


import lombok.*;


/**
 * Created by
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEntry {
	
	private String applicationName;

    private String applicationCode;

    private Long id;
    
    private List<ModuleDTO> lstModules;

}

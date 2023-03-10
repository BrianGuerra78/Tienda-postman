package com.capgemini.academia.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import java.util.Map;

/**
 * Created by
 */
@Entity
@Table(name = "Modules")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleEntity implements Serializable {
	
	@Column(name = "CODE", nullable = false)    
	private String code;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Id
    @SequenceGenerator(name = "seqModule", sequenceName = "MODULE_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seqModule")
    private Long idModule;
    
    @ManyToOne
    @JoinColumn(name = "FK_ID_APPLICATION")
    private ApplicationItem application;

}

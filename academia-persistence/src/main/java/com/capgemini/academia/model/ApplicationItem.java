package com.capgemini.academia.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;

import java.util.List;
import java.util.Map;

/**
 * Created by
 */
@Entity
@Table(name = "Applications")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationItem implements Serializable {
	
	@Column(name = "APPLICATION_NAME", nullable = false)    
	private String applicationName;

    @Column(name = "APPLICATION_CODE", nullable = false)
    private String applicationCode;

    @Id
    @SequenceGenerator(name = "sequ", sequenceName = "application_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "sequ")
    private Long id;
    
    
    @OneToMany(mappedBy="application",cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    private List<ModuleEntity> modules;

}

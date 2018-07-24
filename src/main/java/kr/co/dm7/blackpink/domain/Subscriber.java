package kr.co.dm7.blackpink.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_USER")
public class Subscriber {
	
	@Id
	private long id;
	
	@Column(name="Nick", nullable=false, length=50)
    private String nick;
	
	@Column(name="Name", nullable=false, length=50)
    private String name;
}

package net.ezens.pointnix.notice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Notice {
	@Id
	private int id;
	private String title;
	private String author;
	private String usable;
	private String regdate;
}

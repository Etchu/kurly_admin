package com.kurly.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleProductVO {
	private Integer seq;
	private String prod_name;
	private Date reg_date;
}

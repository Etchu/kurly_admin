package com.kurly.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyVO {
	private Integer mkb_seq;
	private String mkb_name;
	private Date mkb_reg_date;
}

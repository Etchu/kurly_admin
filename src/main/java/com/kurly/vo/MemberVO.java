package com.kurly.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private Integer mkm_seq;
	private String mkm_id;
	private String mkm_name;
	private String mkm_email;
	private String mkm_address;
	private Integer mkm_gen;
	private String mkm_birth;
	private Integer mkm_collect_agree_other;
	private Integer mkm_receive_type;
	private Date mkm_reg_date;
	private Integer mkm_status;
	private Integer mkm_grade;
	private Integer mkm_type;
}

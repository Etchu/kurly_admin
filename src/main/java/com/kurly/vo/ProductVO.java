package com.kurly.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVO {
	private Integer mkp_seq;
	private String mkp_name;
	private String mkp_sub_name;
	private Integer mkp_bi_seq;
	private Integer mkp_ci_seq;
	private Integer mkp_price;
	private Integer mkp_discount;
	private Integer mkp_discount_rate;
	private Integer mkp_pimg_seq;
	private Integer mkp_kurly_only;
	private Integer mkp_point_rate;
	private String mkp_unit;
	private Integer mkp_weight;
	private Integer mkp_early_delivery;
	private Integer mkp_delivery;
	private String mkp_packing_type;
	private String mkp_allergy_info;
	private Date mkp_exp_date;
	private String mkp_relation_seq;
	private Date mkp_reg_date;
	private Integer mkp_count;
	private Integer mkp_buy_count;
	private String category_name;
	private String brand_name;
	private String image_uri;
	private String image_name;
	private Integer mkrp_seq;
	private Integer mkmrp_seq;
}






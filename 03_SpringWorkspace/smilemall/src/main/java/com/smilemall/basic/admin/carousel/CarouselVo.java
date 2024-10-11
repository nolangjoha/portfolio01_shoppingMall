package com.smilemall.basic.admin.carousel;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CarouselVo {

	
	private Integer carousel_num;
	private String carousel_title;
	private String carousel_content;
	private String carousel_up_folder;
	private String carousel_img;
	private String carousel_view_status;
	private Date carousel_date;

	
}

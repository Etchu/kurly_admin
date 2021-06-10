package com.kurly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kurly.vo.LoginVO;
import com.kurly.vo.MemberUpdateVO;
import com.kurly.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	public boolean loginMember(LoginVO vo);
	public List<MemberVO> selectMembers(Integer offset);
	public Integer selectMemberCount();
	public void updateMemberInfo(MemberUpdateVO vo);
	public void deleteMember(Integer seq);
}

package com.bits.groupdetails.service;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.bits.groupdetails.dto.GroupMembersInfoDto;
import com.bits.groupdetails.dto.StudentInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GroupMembersServiceTest {
	
	@InjectMocks
	private GroupMembersService groupMembersService;

	@Mock
	private ObjectMapper mapper;
	
	private File file;
	
    @BeforeEach
    public void setUp()
    {
		ClassLoader classLoader = getClass().getClassLoader();

		file = new File(classLoader.getResource("com/bits/groupdetails/service/students-test.json").getFile());
//        ReflectionTestUtils.setField(groupMembersService,"mapper",mapper);
//        groupMembersService = new GroupMembersService();
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void testGetAllGroupMembersForGivenElectives() {
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			GroupMembersInfoDto groupMembersInfoDto = objectMapper.readValue(file, GroupMembersInfoDto.class);
			when(mapper.readValue(file, GroupMembersInfoDto.class)).thenReturn(groupMembersInfoDto);
			List<StudentInfoDto> studentInfoDtoList = groupMembersService.getAllGroupMembersFrom("Devops", file);
			Assertions.assertTrue(studentInfoDtoList.size() == 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetStudentInfo() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			GroupMembersInfoDto groupMembersInfoDto = objectMapper.readValue(file, GroupMembersInfoDto.class);
			when(mapper.readValue(file, GroupMembersInfoDto.class)).thenReturn(groupMembersInfoDto);
			StudentInfoDto studentInfoDto = groupMembersService.getStudentInfoFrom("12345", file);
			System.out.println(studentInfoDto);
			Assertions.assertTrue(studentInfoDto.getStudentId().equals("12345"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

}

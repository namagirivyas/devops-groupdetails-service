package com.bits.groupmembers.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.bits.groupmembers.dto.GroupMembersInfoDto;
import com.bits.groupmembers.dto.StudentInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
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

		file = new File(classLoader.getResource("com/bits/groupmembers/service/students-test.json").getFile());
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
			assertTrue(studentInfoDtoList.size() == 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}

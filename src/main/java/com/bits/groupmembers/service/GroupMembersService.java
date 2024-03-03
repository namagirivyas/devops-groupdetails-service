package com.bits.groupmembers.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.bits.groupmembers.dto.GroupMembersInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupMembersService {

	public GroupMembersInfoDto getGroupMembersDetails() {
		GroupMembersInfoDto groupMembersInfoDto = null;

		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource("com/bits/groupmembers/service/Students.json").getFile());
		try {
			log.info("Reading student information from JSON file");
			groupMembersInfoDto = mapper.readValue(file, GroupMembersInfoDto.class);

		} catch (IOException e) {
			log.error("Error occured while reading student information", e);
		}
		return groupMembersInfoDto;
	}

}

package com.example.moreveiw.domain.project.service;

import com.example.moreveiw.domain.friend.Bean.SmallBean.UserBean.UserGetByIdSmallBean;
import com.example.moreveiw.domain.image.repository.ImageRepository;
import com.example.moreveiw.domain.member.model.dao.Member;
import com.example.moreveiw.domain.project.model.dao.Project;
import com.example.moreveiw.domain.project.model.dto.response.ObjectResponse;
import com.example.moreveiw.domain.project.repository.ProjectRepository;
import com.example.moreveiw.domain.shape.circle.repository.CircleRepository;
import com.example.moreveiw.domain.shape.line.repository.LineRepository;
import com.example.moreveiw.domain.shape.rectangle.repository.RectangleRepository;
import com.example.moreveiw.domain.text.repository.TextRepository;
import com.example.moreveiw.domain.threeD.repository.ThreeDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserGetByIdSmallBean userGetByIdSmallBean;
    private final ImageRepository imageRepository;
    private final CircleRepository circleRepository;
    private final LineRepository lineRepository;
    private final RectangleRepository rectangleRepository;
    private final TextRepository textRepository;
    private final ThreeDRepository threeDRepository;

    @Transactional
    public ObjectResponse getProjectByObject(Long projectId) {
        return ObjectResponse.builder()
                .images(imageRepository.findByProjectId(projectId))
                .circles(circleRepository.findByProjectId(projectId))
                .lines(lineRepository.findByProjectId(projectId))
                .rectangles(rectangleRepository.findByProjectId(projectId))
                .texts(textRepository.findByProjectId(projectId))
                .threeDs(threeDRepository.findByProjectId(projectId))
                .build();
    }

    public Page<Project> getProjectList(Long memberId, Pageable pageable) {
        Member member = userGetByIdSmallBean.exec(memberId);
        return projectRepository.findByMember(member, pageable);
    }

}

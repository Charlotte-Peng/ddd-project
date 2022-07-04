package org.pj.metaverse.controller.mgmt;

import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.MapSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.UserEntity;
import org.pj.metaverse.entity.repvo.UserRoleInfoRepVO;
import org.pj.metaverse.enums.ResponseEnum;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author pengjie
 * @date 14:10 2022/7/1
 **/
@RestController
@RequestMapping("/mgmt/user")
@RequiredArgsConstructor
@Api(tags = "用户管理相关接口")
public class MgmtUserController {
    private final IUserService userService;
    private final BeanSearcher beanSearcher;
    @GetMapping
    public DataResult<SearchResult<UserRoleInfoRepVO>> getUserInfo(HttpServletRequest request) {
        SearchResult<UserRoleInfoRepVO> search = beanSearcher.search(UserRoleInfoRepVO.class, MapUtils.flat(request.getParameterMap()));
        return new DataResult<>(ResponseEnum.SUCCESS,search);
    }
}

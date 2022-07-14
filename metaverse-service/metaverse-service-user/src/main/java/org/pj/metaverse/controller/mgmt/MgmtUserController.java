package org.pj.metaverse.controller.mgmt;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
import org.pj.metaverse.utils.SearchResultToIPageUtil;
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
    public DataResult<IPage<UserRoleInfoRepVO>> getUserInfo(HttpServletRequest request) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        SearchResult<UserRoleInfoRepVO> search = beanSearcher.search(UserRoleInfoRepVO.class,flat );
        IPage<UserRoleInfoRepVO> data = SearchResultToIPageUtil.convert(search,flat);
        return new DataResult<>(ResponseEnum.SUCCESS,data);
    }
}

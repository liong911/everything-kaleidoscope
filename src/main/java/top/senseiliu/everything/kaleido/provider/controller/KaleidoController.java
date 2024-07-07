package top.senseiliu.everything.kaleido.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.senseiliu.everything.kaleido.common.base.response.Result;
import top.senseiliu.everything.kaleido.facade.dto.KaleidoDTO;
import top.senseiliu.everything.kaleido.manager.kaleido.KaleidoManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取导航数据信息
 * 
 * @author liuguanliang
 * @since 2024-06-29 17:12:34
 */
@Slf4j
@RestController
@RequestMapping("/kaleido")
public class KaleidoController {
   
    @Resource
    private KaleidoManager kaleidoManager;

    /**
     * 查询导航列表
     */
    @GetMapping("/getList")
    public Result<List<KaleidoDTO>> getList() {
        log.info("get kaleidoList");
        return Result.ok(kaleidoManager.getList());
    }

}

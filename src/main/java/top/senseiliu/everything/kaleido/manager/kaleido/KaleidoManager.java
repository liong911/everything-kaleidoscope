package top.senseiliu.everything.kaleido.manager.kaleido;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.senseiliu.everything.kaleido.dal.mapper.kaleido.KaleidoScopeMapper;
import top.senseiliu.everything.kaleido.dal.model.kaleido.KaleidoScope;
import top.senseiliu.everything.kaleido.facade.dto.KaleidoDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 导航管理对象
 * @author liuguanliang
 */
@Slf4j
@Service
public class KaleidoManager extends ServiceImpl<KaleidoScopeMapper, KaleidoScope> {

    public List<KaleidoDTO> getList() {
        // 查询列表
        List<KaleidoScope> list = this.list();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        // 分组转换
        Map<String, Map<String, List<KaleidoScope>>> title2Service2EnvsMap = 
                // 按title进行分组
                list.stream().collect(Collectors.groupingBy(KaleidoScope::getTitle, LinkedHashMap::new,
                        Collectors.collectingAndThen(Collectors.toList(), 
                                // 对service进行优先级排序
                                o -> o.stream().sorted((o1, o2) -> o2.getPriority().compareTo(o1.getPriority()))
                                        // 在对service进行分组
                                        .collect(Collectors.groupingBy(KaleidoScope::getName, LinkedHashMap::new, Collectors.toList())))
        ));

        // 构建出参
        List<KaleidoDTO> result = new ArrayList<>(8);
        title2Service2EnvsMap.forEach((title, service2EnvsMap) -> {
            KaleidoDTO kaleidoDTO = new KaleidoDTO();
            kaleidoDTO.setTitle(title);
            String titleIcon = service2EnvsMap.entrySet().stream()
                    .findFirst().map(Map.Entry::getValue).orElse(Collections.emptyList()).get(0).getTitleIcon();
            kaleidoDTO.setTitleIcon(titleIcon);
            List<KaleidoDTO.ServiceDTO> serviceItems = new ArrayList<>();
            kaleidoDTO.setServiceItems(serviceItems);
            
            // 构建service列表
            service2EnvsMap.forEach((serviceName, envsList) -> {
                KaleidoDTO.ServiceDTO serviceDTO = new KaleidoDTO.ServiceDTO();
                serviceDTO.setName(serviceName);
                serviceDTO.setNameIcon(envsList.get(0).getNameIcon());
                // 构建env列表
                List<KaleidoDTO.EnvironmentDTO> environmentDTOList = envsList.stream().map(o -> {
                    KaleidoDTO.EnvironmentDTO environmentDTO = new KaleidoDTO.EnvironmentDTO();
                    environmentDTO.setTag(o.getTag())
                            .setUrl(o.getUrl())
                            .setAccount(o.getAccount())
                            .setPassword(o.getPassword());
                    return environmentDTO;
                }).collect(Collectors.toList());
                serviceDTO.setEnvironments(environmentDTOList);

                serviceItems.add(serviceDTO);
            });
            
            result.add(kaleidoDTO);
        });
        
        return result;
    }
    
}

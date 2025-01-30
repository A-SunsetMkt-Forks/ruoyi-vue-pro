package cn.iocoder.yudao.module.iot.service.plugin;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.iot.controller.admin.plugin.vo.info.PluginInfoPageReqVO;
import cn.iocoder.yudao.module.iot.controller.admin.plugin.vo.info.PluginInfoSaveReqVO;
import cn.iocoder.yudao.module.iot.dal.dataobject.plugin.IotPluginInfoDO;
import cn.iocoder.yudao.module.iot.enums.plugin.IotPluginStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * IoT 插件信息 Service 接口
 *
 * @author 芋道源码
 */
public interface IotPluginInfoService {

    /**
     * 创建插件信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPluginInfo(@Valid PluginInfoSaveReqVO createReqVO);

    /**
     * 更新插件信息
     *
     * @param updateReqVO 更新信息
     */
    void updatePluginInfo(@Valid PluginInfoSaveReqVO updateReqVO);

    /**
     * 删除插件信息
     *
     * @param id 编号
     */
    void deletePluginInfo(Long id);

    /**
     * 获得插件信息
     *
     * @param id 编号
     * @return 插件信息
     */
    IotPluginInfoDO getPluginInfo(Long id);

    /**
     * 获得插件信息分页
     *
     * @param pageReqVO 分页查询
     * @return 插件信息分页
     */
    PageResult<IotPluginInfoDO> getPluginInfoPage(PluginInfoPageReqVO pageReqVO);

    /**
     * 上传插件的 JAR 包
     *
     * @param id   插件id
     * @param file 文件
     */
    void uploadFile(Long id, MultipartFile file);

    /**
     * 更新插件的状态
     *
     * @param id     插件id
     * @param status 状态 {@link IotPluginStatusEnum}
     */
    void updatePluginStatus(Long id, Integer status);

    /**
     * 获得插件信息列表
     *
     * @return 插件信息列表
     */
    List<IotPluginInfoDO> getPluginInfoList();

    /**
     * 根据状态获得插件信息列表
     *
     * @param status 状态 {@link IotPluginStatusEnum}
     * @return 插件信息列表
     */
    List<IotPluginInfoDO> getPluginInfoListByStatus(Integer status);

    IotPluginInfoDO getPluginInfoByPluginKey(@NotEmpty(message = "插件包标识符不能为空") String pluginKey);

}

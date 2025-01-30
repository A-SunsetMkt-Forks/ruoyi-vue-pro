package cn.iocoder.yudao.module.iot.plugin.common.heartbeta;

import cn.hutool.system.SystemUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.iot.api.device.IotDeviceUpstreamApi;
import cn.iocoder.yudao.module.iot.api.device.dto.control.upstream.IotPluginInstanceHeartbeatReqDTO;
import cn.iocoder.yudao.module.iot.plugin.common.downstream.IotDeviceDownstreamServer;
import cn.iocoder.yudao.module.iot.plugin.common.util.IotPluginCommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * IoT 插件实例心跳 Job
 *
 * 用于定时发送心跳给服务端
 */
@RequiredArgsConstructor
public class IotPluginInstanceHeartbeatJob {

    private final IotDeviceUpstreamApi deviceUpstreamApi;
    private final IotDeviceDownstreamServer deviceDownstreamServer;

    public void init() {
        CommonResult<Boolean> result = deviceUpstreamApi.heartbeatPluginInstance(buildPluginInstanceHeartbeatReqDTO(true));
        // TODO @芋艿：结果的处理
    }

    public void stop() {
        CommonResult<Boolean> result = deviceUpstreamApi.heartbeatPluginInstance(buildPluginInstanceHeartbeatReqDTO(false));
        // TODO @芋艿：结果的处理
    }

    @Scheduled(initialDelay = 3, fixedRate = 3, timeUnit = TimeUnit.MINUTES) // 3 分钟执行一次
    public void execute() {
        CommonResult<Boolean> result = deviceUpstreamApi.heartbeatPluginInstance(buildPluginInstanceHeartbeatReqDTO(true));
        // TODO @芋艿：结果的处理
    }

    private IotPluginInstanceHeartbeatReqDTO buildPluginInstanceHeartbeatReqDTO(Boolean online) {
        // TODO @芋艿：pluginKey 的获取？？？
        return new IotPluginInstanceHeartbeatReqDTO()
                .setPluginKey("yudao-module-iot-plugin-http").setProcessId(IotPluginCommonUtils.getProcessId())
                .setHostIp(SystemUtil.getHostInfo().getAddress()).setDownstreamPort(deviceDownstreamServer.getPort())
                .setOnline(online);
    }

}

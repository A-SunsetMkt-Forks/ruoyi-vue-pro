package cn.iocoder.yudao.module.ai.controller.admin.knowledge;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ai.controller.admin.knowledge.vo.document.AiKnowledgeDocumentPageReqVO;
import cn.iocoder.yudao.module.ai.controller.admin.knowledge.vo.document.AiKnowledgeDocumentRespVO;
import cn.iocoder.yudao.module.ai.controller.admin.knowledge.vo.document.AiKnowledgeDocumentUpdateReqVO;
import cn.iocoder.yudao.module.ai.controller.admin.knowledge.vo.document.AiKnowledgeDocumentCreateListReqVO;
import cn.iocoder.yudao.module.ai.controller.admin.knowledge.vo.knowledge.AiKnowledgeDocumentCreateReqVO;
import cn.iocoder.yudao.module.ai.dal.dataobject.knowledge.AiKnowledgeDocumentDO;
import cn.iocoder.yudao.module.ai.service.knowledge.AiKnowledgeDocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

// TODO @芋艿：增加权限标识
@Tag(name = "管理后台 - AI 知识库文档")
@RestController
@RequestMapping("/ai/knowledge/document")
@Validated
public class AiKnowledgeDocumentController {

    @Resource
    private AiKnowledgeDocumentService documentService;

    @GetMapping("/page")
    @Operation(summary = "获取文档分页")
    public CommonResult<PageResult<AiKnowledgeDocumentRespVO>> getKnowledgeDocumentPage(
            @Valid AiKnowledgeDocumentPageReqVO pageReqVO) {
        PageResult<AiKnowledgeDocumentDO> pageResult = documentService.getKnowledgeDocumentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AiKnowledgeDocumentRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获取文档详情")
    public CommonResult<AiKnowledgeDocumentRespVO> getKnowledgeDocument(@RequestParam("id") Long id) {
        AiKnowledgeDocumentDO document = documentService.getKnowledgeDocument(id);
        return success(BeanUtils.toBean(document, AiKnowledgeDocumentRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "新建文档")
    public CommonResult<Long> createKnowledgeDocument(@RequestBody @Valid AiKnowledgeDocumentCreateReqVO reqVO) {
        Long id = documentService.createKnowledgeDocument(reqVO);
        return success(id);
    }

    @PostMapping("/create-list")
    @Operation(summary = "批量新建文档")
    public CommonResult<List<Long>> createKnowledgeDocumentList(
            @RequestBody @Valid AiKnowledgeDocumentCreateListReqVO reqVO) {
        List<Long> ids = documentService.createKnowledgeDocumentList(reqVO);
        return success(ids);
    }

    @PutMapping("/update")
    @Operation(summary = "更新文档")
    public CommonResult<Boolean> updateKnowledgeDocument(@Valid @RequestBody AiKnowledgeDocumentUpdateReqVO reqVO) {
        documentService.updateKnowledgeDocument(reqVO);
        return success(true);
    }

}

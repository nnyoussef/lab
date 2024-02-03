package nyo.lu.appdeployer.jee.app.restcontroller;

import nyo.lu.appdeployer.jee.app.dto.request.IndexingRequest;
import nyo.lu.appdeployer.jee.app.dto.response.exceptions.DirectoryManagementException;
import nyo.lu.appdeployer.jee.functions.indexation.*;
import nyo.lu.appdeployer.jee.functions.indexation.file.InsertIndicesInEsFunction;
import nyo.lu.appdeployer.jee.functions.indexation.file.MoveFilesToDirectoryWithIndexNameFunction;
import nyo.lu.appdeployer.jee.functions.indexation.file.MoveFilesToDirectoryWithIndexNameFunction.Input;
import nyo.lu.appdeployer.jee.functions.indexation.file.RegisterFileWithIndicesInDbFunction;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/index_management")
public class IndexManagementRestController extends BaseRestController {

    @PostMapping(value = "/create/index")
    @ResponseStatus(NO_CONTENT)
    public void createIndex(@RequestBody IndexingRequest indexingRequest) throws DirectoryManagementException {
        functionsChainer.run(indexingRequest,
                IsIndexCreatedFunction.class,
                CreateIndexFunction.class,
                CreateIndexMappingsFunction.class,
                DbSaveIndexNameFunction.class,
                DbSaveIndexMappingsFunction.class);
    }

    @GetMapping(value = "/get_all_indices")
    public Map<String, Object> getAllIndices() {
        return functionsChainer.runWithResult(null, Map.of(), GetAllIndexesWithMappingFunction.class);
    }

    @PostMapping("index/files")
    @ResponseStatus(NO_CONTENT)
    public void indexFiles(@RequestParam("files") MultipartFile[] files,
                           @RequestParam("indices") String indices,
                           @RequestParam("index") String index) {
        functionsChainer.run(new Input(files, indices, index),
                MoveFilesToDirectoryWithIndexNameFunction.class,
                InsertIndicesInEsFunction.class,
                RegisterFileWithIndicesInDbFunction.class);

    }
}

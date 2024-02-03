package nyo.lu.appdeployer.jee.functions.directory;

import nyo.lu.appdeployer.jee.app.dto.response.DirectoryTree;
import nyo.lu.appdeployer.jee.functions.AbstractInteractor;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.StreamSupport;

@Component
public class DirectoryFetchAllFunction extends AbstractInteractor implements Function<Void, DirectoryTree> {

    @Override
    public DirectoryTree apply(Void unused) {
        DirectoryTree directoryTree = new DirectoryTree();
        directoryRepository.getAllInfoForAllDirectories().forEach(data -> {
            final DirectoryTree[] currentDirectoryTree = {directoryTree};
            String directory = data.getDirPath().replace(applicationStorage.getBasepath(), "");
            LocalDateTime created = data.getCreated();
            LocalDateTime update = data.getUpdated();

            StreamSupport.stream(Paths.get(directory).spliterator(), false)
                    .map(Path::toString)
                    .forEach(p -> currentDirectoryTree[0] = currentDirectoryTree[0].getSubTree().computeIfAbsent(p, k -> new DirectoryTree()));

            currentDirectoryTree[0].getLabels().put(data.getLabel(), data.getLabelValue());

            currentDirectoryTree[0].setFullPath(directory);
            currentDirectoryTree[0].setCreated(created);
            currentDirectoryTree[0].setUpdatedAt(update);
        });

        return directoryTree;
    }


}

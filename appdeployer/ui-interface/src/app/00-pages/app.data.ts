import {GenericModelService} from "../02-services/generic-service/generic-model.service";
import {AppDataModel, AppDataModelAccessor} from "./app.types";
import {Injectable} from "@angular/core";
import {DirectoryTree} from "./01-directory-overview/directory-overview.types";

@Injectable()
export class AppData extends GenericModelService<AppDataModel, AppDataModelAccessor> {

  public override defineModelAccessors(): AppDataModelAccessor {
    return {
      getAllDirectories: () => this.state.availableDirectories.values,
      storeDirectoriesInfo: (data: DirectoryTree) => this.state.availableDirectories.values = data,
      setDirectoriesInfoDirtyState: (flag: boolean) => this.state.availableDirectories.dirty = flag,
      getDirectoriesInfoDirtyState: () => this.state.availableDirectories.dirty
    }
  }

  constructor() {
    super();
  }

  defineDefaultState(): AppDataModel {
    return {
      globalData: {},
      availableDirectories: {
        dirty: true,
        values: {
          subTree: {},
          created: [0, 0, 0, 0, 0, 0],
          updatedAt: [0, 0, 0, 0, 0, 0],
          labels: {}
        }
      }
    };
  }


}

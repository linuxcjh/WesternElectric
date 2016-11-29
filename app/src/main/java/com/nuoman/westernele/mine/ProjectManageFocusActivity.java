package com.nuoman.westernele.mine;

import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManService;
import com.nuoman.westernele.filter.SelectItemModel;
import com.nuoman.westernele.login.model.CompanyInfoModel;
import com.nuoman.westernele.projectmanage.ProjectManageActivity;
import com.nuoman.westernele.projectmanage.model.ProjectModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectManageFocusActivity extends ProjectManageActivity {


    List<ProjectModel> temp = new ArrayList<>();


    @Override
    public void obtainData(Object data, String methodIndex, int status, Map<String, String> parameterMap) {
        switch (methodIndex) {
            case NuoManService.GETPROJECTLISTCONDITION:
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                if (data != null) {
                    List<ProjectModel> model = (List<ProjectModel>) data;

                    for (int i = 0; i < model.size(); i++) {
                        if (model.get(i).getIsCollected().equals("1")) {
                            temp.add(model.get(i));
                        }
                    }
                    mAdapter.setData(temp);

                }
                break;
            case NuoManService.GETPROJECTCONDITIONS:
                if (data != null) {
                    List<CompanyInfoModel> models = (List<CompanyInfoModel>) data;
                    List<SelectItemModel> filterSingle = new ArrayList<>();
                    for (int i = 0; i < models.size(); i++) {
                        filterSingle.add(new SelectItemModel(models.get(i).getId(), models.get(i).getDataName()));
                    }
                    filterLayout.setSingleFilterData(filterSingle);
                }
                filterLayout.setOnSelectListener(this);
                List<SelectItemModel> filterSingle = new ArrayList<>();
                filterSingle.add(new SelectItemModel("1", "以时间正序排列"));
                filterSingle.add(new SelectItemModel("2", "以时间反序排列"));
                filterSingle.add(new SelectItemModel("3", "以项目名称正序排列"));
                filterSingle.add(new SelectItemModel("4", "以项目名称反序排列"));
                filterLayout.setSingleFilterData(filterSingle);
                break;
            case NuoManService.PROJECTCOLLECTION:
                if (status == 1) {
                    commonPresenter.invokeInterfaceObtainData(true, "appProjectCtrl", NuoManService.GETPROJECTLISTCONDITION, transModel, new TypeToken<List<ProjectModel>>() {
                    });
                }
                break;
        }

    }


}

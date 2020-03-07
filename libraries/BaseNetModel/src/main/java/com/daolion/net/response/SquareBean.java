package com.daolion.net.response;

import java.util.List;

public class SquareBean {

    /**
     * total : 5
     * pageSize : 20
     * totalPage : 1
     * page : 1
     * offset : 0
     * data : [{"id":"6f8cfc6fcce1a9e7752d15b375aac6bf","externalId":"173290efc3f744c1a47a2538733826cf","projectName":"上海11/22项目12","projectCode":"1234","detailAddress":"虹桥火车站","houseQuantity":11,"cityName":"朝阳区","cityCode":"110105","provinceName":"北京市","provinceCode":"110000","groundArea":"11","buildingArea":"11","tenantId":1,"status":1,"creatorId":1,"createTime":"2018-11-22T15:16:46.000+0800","updateTime":"2019-01-11T10:23:39.000+0800","creator":"superadmin","externalStatus":1,"smartQuantity":33,"coverage":"300.00%"},{"id":"f4195b8f6215cf30d811a9db55c6dd2b","externalId":"11","projectName":"mp11","projectCode":"11","detailAddress":"111","houseQuantity":11,"cityName":"朝阳区","cityCode":"110105","provinceName":"北京市","provinceCode":"110000","groundArea":"11","buildingArea":"11","tenantId":1,"status":1,"creatorId":1,"createTime":"2018-11-16T19:00:48.000+0800","updateTime":"2018-11-16T19:00:48.000+0800","creator":"superadmin","externalStatus":1,"smartQuantity":0,"coverage":"0.00%"},{"id":"2792eaf17790bae01114344e635c67e6","externalId":"rt","projectName":"11mp","projectCode":"12mp","detailAddress":"2122","houseQuantity":45,"cityName":"朝阳区","cityCode":"110105","provinceName":"北京市","provinceCode":"110000","groundArea":"455","buildingArea":"5","tenantId":1,"status":1,"creatorId":1,"createTime":"2018-11-16T18:53:26.000+0800","updateTime":"2018-11-16T18:53:26.000+0800","creator":"superadmin","externalStatus":1,"smartQuantity":0,"coverage":"0.00%"},{"id":"99a7cf649983b26f786545c5ae715afe","externalId":"c20223565c284101a92e231ed1e099e1","projectName":"蓝光信服111","projectCode":"2345","detailAddress":"121212","houseQuantity":12,"cityName":"北辰区","cityCode":"120113","provinceName":"天津市","provinceCode":"120000","groundArea":"132342","buildingArea":"423432","tenantId":1,"status":1,"creatorId":1,"createTime":"2018-11-15T10:22:25.000+0800","updateTime":"2018-11-16T15:10:26.000+0800","creator":"superadmin","externalStatus":1,"smartQuantity":0,"coverage":"0.00%"},{"id":"9d151e174173215f39c9d11f064b3cb2","externalId":"3480ec63ba2f4f00aff3fe28dd5a114b","projectName":"测试组织机构树关联11","projectCode":"123","detailAddress":"测试组织机构树关联","houseQuantity":123,"cityName":"朝阳区","cityCode":"110105","provinceName":"北京市","provinceCode":"110000","groundArea":"","buildingArea":"","tenantId":1,"status":1,"creatorId":1,"createTime":"2018-10-30T16:39:03.000+0800","updateTime":"2018-11-12T19:15:43.000+0800","creator":"superadmin","externalStatus":1,"smartQuantity":0,"coverage":"0.00%"}]
     * message : success
     * status : 200
     */

    private int total;
    private int pageSize;
    private int totalPage;
    private int page;
    private int offset;
    private String message;
    private int status;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6f8cfc6fcce1a9e7752d15b375aac6bf
         * externalId : 173290efc3f744c1a47a2538733826cf
         * projectName : 上海11/22项目12
         * projectCode : 1234
         * detailAddress : 虹桥火车站
         * houseQuantity : 11
         * cityName : 朝阳区
         * cityCode : 110105
         * provinceName : 北京市
         * provinceCode : 110000
         * groundArea : 11
         * buildingArea : 11
         * tenantId : 1
         * status : 1
         * creatorId : 1
         * createTime : 2018-11-22T15:16:46.000+0800
         * updateTime : 2019-01-11T10:23:39.000+0800
         * creator : superadmin
         * externalStatus : 1
         * smartQuantity : 33
         * coverage : 300.00%
         */

        private String id;
        private String externalId;
        private String projectName;
        private String projectCode;
        private String detailAddress;
        private int houseQuantity;
        private String cityName;
        private String cityCode;
        private String provinceName;
        private String provinceCode;
        private String groundArea;
        private String buildingArea;
        private int tenantId;
        private int status;
        private int creatorId;
        private String createTime;
        private String updateTime;
        private String creator;
        private int externalStatus;
        private int smartQuantity;
        private String coverage;

        private boolean isChecked;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getExternalId() {
            return externalId;
        }

        public void setExternalId(String externalId) {
            this.externalId = externalId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public int getHouseQuantity() {
            return houseQuantity;
        }

        public void setHouseQuantity(int houseQuantity) {
            this.houseQuantity = houseQuantity;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getGroundArea() {
            return groundArea;
        }

        public void setGroundArea(String groundArea) {
            this.groundArea = groundArea;
        }

        public String getBuildingArea() {
            return buildingArea;
        }

        public void setBuildingArea(String buildingArea) {
            this.buildingArea = buildingArea;
        }

        public int getTenantId() {
            return tenantId;
        }

        public void setTenantId(int tenantId) {
            this.tenantId = tenantId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public int getExternalStatus() {
            return externalStatus;
        }

        public void setExternalStatus(int externalStatus) {
            this.externalStatus = externalStatus;
        }

        public int getSmartQuantity() {
            return smartQuantity;
        }

        public void setSmartQuantity(int smartQuantity) {
            this.smartQuantity = smartQuantity;
        }

        public String getCoverage() {
            return coverage;
        }

        public void setCoverage(String coverage) {
            this.coverage = coverage;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", externalId='" + externalId + '\'' +
                    ", projectName='" + projectName + '\'' +
                    ", projectCode='" + projectCode + '\'' +
                    ", detailAddress='" + detailAddress + '\'' +
                    ", houseQuantity=" + houseQuantity +
                    ", cityName='" + cityName + '\'' +
                    ", cityCode='" + cityCode + '\'' +
                    ", provinceName='" + provinceName + '\'' +
                    ", provinceCode='" + provinceCode + '\'' +
                    ", groundArea='" + groundArea + '\'' +
                    ", buildingArea='" + buildingArea + '\'' +
                    ", tenantId=" + tenantId +
                    ", status=" + status +
                    ", creatorId=" + creatorId +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", creator='" + creator + '\'' +
                    ", externalStatus=" + externalStatus +
                    ", smartQuantity=" + smartQuantity +
                    ", coverage='" + coverage + '\'' +
                    '}';
        }
    }
}

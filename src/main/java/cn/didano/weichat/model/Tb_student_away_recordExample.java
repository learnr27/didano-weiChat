package cn.didano.weichat.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tb_student_away_recordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Tb_student_away_recordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andFileImgIdIsNull() {
            addCriterion("file_img_id is null");
            return (Criteria) this;
        }

        public Criteria andFileImgIdIsNotNull() {
            addCriterion("file_img_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileImgIdEqualTo(Integer value) {
            addCriterion("file_img_id =", value, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdNotEqualTo(Integer value) {
            addCriterion("file_img_id <>", value, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdGreaterThan(Integer value) {
            addCriterion("file_img_id >", value, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_img_id >=", value, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdLessThan(Integer value) {
            addCriterion("file_img_id <", value, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdLessThanOrEqualTo(Integer value) {
            addCriterion("file_img_id <=", value, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdIn(List<Integer> values) {
            addCriterion("file_img_id in", values, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdNotIn(List<Integer> values) {
            addCriterion("file_img_id not in", values, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdBetween(Integer value1, Integer value2) {
            addCriterion("file_img_id between", value1, value2, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andFileImgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("file_img_id not between", value1, value2, "fileImgId");
            return (Criteria) this;
        }

        public Criteria andSubImgIsNull() {
            addCriterion("sub_img is null");
            return (Criteria) this;
        }

        public Criteria andSubImgIsNotNull() {
            addCriterion("sub_img is not null");
            return (Criteria) this;
        }

        public Criteria andSubImgEqualTo(String value) {
            addCriterion("sub_img =", value, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgNotEqualTo(String value) {
            addCriterion("sub_img <>", value, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgGreaterThan(String value) {
            addCriterion("sub_img >", value, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgGreaterThanOrEqualTo(String value) {
            addCriterion("sub_img >=", value, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgLessThan(String value) {
            addCriterion("sub_img <", value, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgLessThanOrEqualTo(String value) {
            addCriterion("sub_img <=", value, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgLike(String value) {
            addCriterion("sub_img like", value, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgNotLike(String value) {
            addCriterion("sub_img not like", value, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgIn(List<String> values) {
            addCriterion("sub_img in", values, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgNotIn(List<String> values) {
            addCriterion("sub_img not in", values, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgBetween(String value1, String value2) {
            addCriterion("sub_img between", value1, value2, "subImg");
            return (Criteria) this;
        }

        public Criteria andSubImgNotBetween(String value1, String value2) {
            addCriterion("sub_img not between", value1, value2, "subImg");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdIsNull() {
            addCriterion("file_sub_img_id is null");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdIsNotNull() {
            addCriterion("file_sub_img_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdEqualTo(Integer value) {
            addCriterion("file_sub_img_id =", value, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdNotEqualTo(Integer value) {
            addCriterion("file_sub_img_id <>", value, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdGreaterThan(Integer value) {
            addCriterion("file_sub_img_id >", value, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_sub_img_id >=", value, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdLessThan(Integer value) {
            addCriterion("file_sub_img_id <", value, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdLessThanOrEqualTo(Integer value) {
            addCriterion("file_sub_img_id <=", value, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdIn(List<Integer> values) {
            addCriterion("file_sub_img_id in", values, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdNotIn(List<Integer> values) {
            addCriterion("file_sub_img_id not in", values, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdBetween(Integer value1, Integer value2) {
            addCriterion("file_sub_img_id between", value1, value2, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andFileSubImgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("file_sub_img_id not between", value1, value2, "fileSubImgId");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
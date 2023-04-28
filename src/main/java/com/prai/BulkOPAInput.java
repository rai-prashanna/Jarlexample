package com.prai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "method",
        "resources",
        "roles"
})
@Generated("jsonschema2pojo")
public class BulkOPAInput implements Serializable {

    @JsonProperty("method")
    private String method;
    @JsonProperty("resources")
    private List<String> resources = new ArrayList<String>();
    @JsonProperty("roles")
    private List<String> roles = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -7042078676187618513L;

    /**
     * No args constructor for use in serialization
     */
    public BulkOPAInput() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulkOPAInput that = (BulkOPAInput) o;

        if (!method.equals(that.method)) return false;
        if (!resources.equals(that.resources)) return false;
        if (!roles.equals(that.roles)) return false;
        if (!additionalProperties.equals(that.additionalProperties)) return false;

        return true;
    }

    /**
     * @param method
     * @param roles
     * @param resources
     */
    public BulkOPAInput(String method, List<String> resources, List<String> roles) {
        super();
        this.method = method;
        this.resources = resources;
        this.roles = roles;
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty("resources")
    public List<String> getResources() {
        return resources;
    }

    @JsonProperty("resources")
    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "BulkOPAInput{" +
                "method='" + method + '\'' +
                ", resources=" + resources +
                ", roles=" + roles +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.resources == null) ? 0 : this.resources.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.method == null) ? 0 : this.method.hashCode()));
        result = ((result * 31) + ((this.roles == null) ? 0 : this.roles.hashCode()));
        return result;
    }


}


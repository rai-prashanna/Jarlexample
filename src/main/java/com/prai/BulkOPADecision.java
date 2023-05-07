package com.prai;


import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "batch_allow"
})
@Generated("jsonschema2pojo")
public class BulkOPADecision implements Serializable {

    @JsonProperty("batch_allow")
    private List<String> batchAllow = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -9219841845067788385L;

    /**
     * No args constructor for use in serialization
     */
    public BulkOPADecision() {
    }

    /**
     * @param batchAllow
     */
    public BulkOPADecision(List<String> batchAllow) {
        super();
        this.batchAllow = batchAllow;
    }

    @JsonProperty("batch_allow")
    public List<String> getBatchAllow() {
        return batchAllow;
    }

    @JsonProperty("batch_allow")
    public void setBatchAllow(List<String> batchAllow) {
        this.batchAllow = batchAllow;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulkOPADecision that = (BulkOPADecision) o;

        if (!batchAllow.equals(that.batchAllow)) return false;
        if (!additionalProperties.equals(that.additionalProperties)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = batchAllow.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BulkOPADecision{" +
                "batchAllow=" + batchAllow +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

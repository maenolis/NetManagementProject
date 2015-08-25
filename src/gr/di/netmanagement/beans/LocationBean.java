package gr.di.netmanagement.beans;

import org.apache.commons.math3.ml.clustering.Clusterable;

/**
 * The Interface LocationBean.
 */
public interface LocationBean extends Bean, Clusterable {

	public Location getLocation();
}

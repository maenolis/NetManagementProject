package gr.di.netmanagement.processdata;

import org.apache.commons.math3.ml.distance.DistanceMeasure;

public class SpaceDistance implements DistanceMeasure {

	private static final long serialVersionUID = 1L;

	@Override
	public double compute(final double[] paramArrayOfDouble1,
			final double[] paramArrayOfDouble2) {

		final double lat1 = paramArrayOfDouble1[0];
		final double lon1 = paramArrayOfDouble1[1];
		final double lat2 = paramArrayOfDouble2[0];
		final double lon2 = paramArrayOfDouble2[1];

		/* Radius of the earth. */
		final int R = 6371;

		final Double latDistance = Math.toRadians(lat2 - lat1);
		final Double lonDistance = Math.toRadians(lon2 - lon1);
		final Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2)
				* Math.sin(lonDistance / 2);
		final Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		/* Convert to meters. */
		double distance = R * c * 1000;

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);

	}

}

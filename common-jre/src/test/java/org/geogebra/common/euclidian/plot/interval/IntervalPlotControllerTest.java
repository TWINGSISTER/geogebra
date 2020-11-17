package org.geogebra.common.euclidian.plot.interval;

import org.geogebra.common.BaseUnitTest;
import org.geogebra.common.euclidian.EuclidianView;
import org.geogebra.common.euclidian.GeneralPathClipped;
import org.geogebra.common.jre.headless.AppCommon;
import org.geogebra.common.kernel.geos.GeoFunction;
import org.geogebra.common.kernel.interval.Interval;
import org.geogebra.common.kernel.interval.IntervalFunctionSampler;
import org.geogebra.common.kernel.interval.IntervalTuple;
import org.junit.Before;
import org.junit.Test;

public class IntervalPlotControllerTest extends BaseUnitTest {
	public static final int NUMBER_OF_SAMPLES = 100;
	private IntervalPlotController controller;
	private AppCommon app;
	private EuclidianView view;
	private GeneralPathClipped gp = new GeneralPathMock(null);

	@Before
	public void setUp() {
		app = getApp();
		view = getApp().getActiveEuclidianView();
	}

	@Test
	public void testEqualsSin() {
		IntervalTuple range = createRange(-15.0, 15.0, -3.0, 3.0);
		IntervalPlotModel model = createModel(range, createSampler("sin(x)", range));
		IntervalPath path = new IntervalPath(gp, view, model);
		model.setPath(path);

	}

	private IntervalFunctionSampler createSampler(String functionString,
			IntervalTuple range) {
		GeoFunction function = add(functionString);
		return new IntervalFunctionSampler(function, range, NUMBER_OF_SAMPLES);
	}

	private IntervalPlotModel createModel(IntervalTuple range, IntervalFunctionSampler sampler) {
		return new IntervalPlotModel(range, sampler, view);
	}

	private IntervalTuple createRange(double lowX, double highX, double lowY, double highY) {
		Interval x = new Interval(lowX, highX);
		Interval y = new Interval(lowY, highY);
		return new IntervalTuple(x, y);
	}
}
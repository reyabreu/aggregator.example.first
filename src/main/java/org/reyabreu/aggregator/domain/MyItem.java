package org.reyabreu.aggregator.domain;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Just a simple Itme to understand how aggregator collects items and it what
 * order
 * 
 * @author Reynaldo
 *
 */
public class MyItem {

	private final Integer id;
	private final String contents;

	public MyItem(final Integer id, final String contents) {
		super();
		this.id = id;
		this.contents = contents;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof MyItem) {
			MyItem other = (MyItem) obj;
			return Objects.equals(contents, other.contents);
		} else {
			return false;
		}
	}

	public String getContents() {
		return contents;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contents);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}

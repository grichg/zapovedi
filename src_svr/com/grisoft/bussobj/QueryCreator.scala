package com.grisoft.bussobj
import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.Query.FilterOperator
import com.google.appengine.api.datastore.Query.SortDirection

object QueryCreator {

  def makeQuery(): Query = {
    val q = new Query("Person");

    q.addFilter("gender", FilterOperator.EQUAL, "female");

    q.addSort("age", SortDirection.ASCENDING);
    q
    //datastore.prepare(findFemalesQuery).asList(FetchOptions.Builder.withLimit(10));
  }
}
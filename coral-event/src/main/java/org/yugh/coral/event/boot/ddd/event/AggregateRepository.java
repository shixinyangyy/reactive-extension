/**
 *
 */
package org.yugh.coral.event.boot.ddd.event;

import org.springframework.data.repository.CrudRepository;

public interface AggregateRepository extends CrudRepository<Aggregate, Long> {

}
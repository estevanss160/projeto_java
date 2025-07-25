package com.example.demo.adapters.out;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Champions;
import com.example.demo.domain.ports.ChampionsRepository;

@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {
	
	private JdbcTemplate jdbcTemplate;
	private final RowMapper<Champions> rowMapper;
	
	public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.rowMapper = (rs, rowNum) -> new Champions(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getString("role"),
				rs.getString("lore"),
				rs.getString("image_url")
				);
	}
	@Override
	public List<Champions> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM CHAMPIONS ", rowMapper);
	}
	@Override
	public Optional<Champions> findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM CHAMPIONS WHERE ID=?";
		List<Champions> champion = jdbcTemplate.query(sql, rowMapper, id);
		return champion.stream().findFirst();
	}
}
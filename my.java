
	public List<EasyUITreeResult> findCachedCatListById(Long parentId) {
		// 1.先查询缓存
		String key = "ITEM_CAT_" + parentId; 
		
		List<EasyUITreeResult> list = new ArrayList<EasyUITreeResult>();
		
		
		String jsonData = jedis.get(key);
		try {
			if (StringUtils.isEmpty(jsonData)) { // 缓存中没数据
				list = findCatListById(parentId);

				jsonData = objectMapper.writeValueAsString(list);

				jedis.set(key, jsonData);

			} else {
				
				list = objectMapper.readValue(jsonData, list.getClass());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

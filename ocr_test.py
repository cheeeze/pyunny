def ocr_product_oxford(headers, params, data):
    conn = http.HTTTPSConnection('wetus.api.cognitive.microsoft.com')
    conn.request('POST', '/vision/v1.0/ocr?%s' % params, data, headers)
    response = conn.getresponse()
    data = response.read().decode()
    print(data+'\n')
    print_text(data)
    conn.close()
    return


if __name__ == '__main__':
    headers = {
        # 헤더 요청
        'Content-Type': 'application/octest-stream',
        # project oxford 인증키 입력
        'Ocp-Apim-Subscription-Key': '',
    }
    params = urllib.parse.urlencode({
        # 파라미터 요청
        'language': 'unk',
        'detectOrientation': 'true',
    })
    data = open('starbucks.jpg', 'rb').read()

    try:
        image_file = 'scannd'

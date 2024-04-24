PGDMP  %                    |            agency    16.2    16.2 "    5           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            6           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            7           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            8           1262    16923    agency    DATABASE     h   CREATE DATABASE agency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE agency;
                postgres    false            �            1259    16956    hotels    TABLE     �  CREATE TABLE public.hotels (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    car_park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotels;
       public         heap    postgres    false            �            1259    16955    hotels_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotels ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotels_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16964    pensions    TABLE     �   CREATE TABLE public.pensions (
    pension_id integer NOT NULL,
    pension_hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
    DROP TABLE public.pensions;
       public         heap    postgres    false            �            1259    16963    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pensions ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    25151    reservations    TABLE     y  CREATE TABLE public.reservations (
    reservation_id integer NOT NULL,
    reservation_room_id integer NOT NULL,
    check_in date NOT NULL,
    check_out date NOT NULL,
    total_price double precision NOT NULL,
    guest_count integer NOT NULL,
    guest_name text NOT NULL,
    guest_citizen_id text NOT NULL,
    guest_mail text NOT NULL,
    guest_phone text NOT NULL
);
     DROP TABLE public.reservations;
       public         heap    postgres    false            �            1259    25150    reservations_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservations ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservations_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    226            �            1259    25143    rooms    TABLE     "  CREATE TABLE public.rooms (
    room_id integer NOT NULL,
    room_hotel_id integer NOT NULL,
    room_pension_id integer NOT NULL,
    room_season_id integer NOT NULL,
    room_type text NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.rooms;
       public         heap    postgres    false            �            1259    25142    rooms_room_id_seq    SEQUENCE     �   ALTER TABLE public.rooms ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.rooms_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    25137    seasons    TABLE     �   CREATE TABLE public.seasons (
    season_id integer NOT NULL,
    season_hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
    DROP TABLE public.seasons;
       public         heap    postgres    false            �            1259    25136    seasons_id_seq    SEQUENCE     �   ALTER TABLE public.seasons ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.seasons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    16925    users    TABLE     �   CREATE TABLE public.users (
    user_id integer NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16924    user_user_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            *          0    16956    hotels 
   TABLE DATA           �   COPY public.hotels (hotel_id, hotel_name, hotel_address, hotel_mail, hotel_phone, hotel_star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    218    )       ,          0    16964    pensions 
   TABLE DATA           N   COPY public.pensions (pension_id, pension_hotel_id, pension_type) FROM stdin;
    public          postgres    false    220   Y*       2          0    25151    reservations 
   TABLE DATA           �   COPY public.reservations (reservation_id, reservation_room_id, check_in, check_out, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone) FROM stdin;
    public          postgres    false    226   �*       0          0    25143    rooms 
   TABLE DATA           �   COPY public.rooms (room_id, room_hotel_id, room_pension_id, room_season_id, room_type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    224   �*       .          0    25137    seasons 
   TABLE DATA           V   COPY public.seasons (season_id, season_hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    222   �+       (          0    16925    users 
   TABLE DATA           G   COPY public.users (user_id, username, password, user_role) FROM stdin;
    public          postgres    false    216   �+       9           0    0    hotels_hotel_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotels_hotel_id_seq', 17, true);
          public          postgres    false    217            :           0    0    pension_pension_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pension_pension_id_seq', 18, true);
          public          postgres    false    219            ;           0    0    reservations_reservation_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.reservations_reservation_id_seq', 1, false);
          public          postgres    false    225            <           0    0    rooms_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.rooms_room_id_seq', 4, true);
          public          postgres    false    223            =           0    0    seasons_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.seasons_id_seq', 7, true);
          public          postgres    false    221            >           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 17, true);
          public          postgres    false    215            �           2606    16960    hotels hotels_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (hotel_id);
 <   ALTER TABLE ONLY public.hotels DROP CONSTRAINT hotels_pkey;
       public            postgres    false    218            �           2606    16968    pensions pension_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.pensions
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 ?   ALTER TABLE ONLY public.pensions DROP CONSTRAINT pension_pkey;
       public            postgres    false    220            �           2606    25157    reservations reservations_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (reservation_id);
 H   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_pkey;
       public            postgres    false    226            �           2606    25147    rooms rooms_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (room_id);
 :   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_pkey;
       public            postgres    false    224            �           2606    25141    seasons seasons_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.seasons
    ADD CONSTRAINT seasons_pkey PRIMARY KEY (season_id);
 >   ALTER TABLE ONLY public.seasons DROP CONSTRAINT seasons_pkey;
       public            postgres    false    222            �           2606    16931    users user_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 9   ALTER TABLE ONLY public.users DROP CONSTRAINT user_pkey;
       public            postgres    false    216            *   )  x�U��N�0��O��/����1B�E$˘&o*�𧄂��3����˂�b$������-^�:Π;=�v����U����	�X:`C-���h>���8����di��S��"�K��PQ�5�6�oL��=^]���b/��V��hc���A�6Y�)�����E��lk��S�h)./*z�Q�⹑j\�G^5�W�w�՝�pGɶl��h�dԺ�{�oc9�	�꫺-B&�����Ҕ�o�8gM�]�|%�eI<b����[�ݦ���k:�D��f�;���      ,   u   x�3�4��))JT�H-R8:?�R�%1#3��А�����!.]� ]�&�����9��9
�E���+����($��d�p5 ���$*x'f�%���eh�	ԅn�'PUa� �0=�      2      x������ � �      0      x�U�M
�@��/����_/�U�u�U��u@f���Z������G����4v�Rf�3#j	$���"���Z���Gx�Ś^�CP�C4nm�e톖�.���_/}!�d�l%��?>m��t>񏞈>��+�      .   H   x�u��	 !�s�e2mf��cDO9	A��ES����)�h\�mrp�G����Du7@/F̂�."?&�       (   �   x�]�A
�0EדS���jׂYmu+��P�I����_q�������[Oخ��i:��Z���wq�u�ܜ3�Q����4�h($�������@���>�B�B�k�+�M`O��{k�yו3�     